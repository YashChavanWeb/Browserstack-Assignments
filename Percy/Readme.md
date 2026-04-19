My percy project with automate: https://percy.io/f653eabd/web/automate-percy-test-afcef2fb


## Percy

Visual testing tool that is used to mark and find the visual bugs

Functional testing is about the features, but checkihng whether the website's design is as intended
on browsers, devices, resolutions in a manual is very time consuming

percy can handle this

##

There are two components of percy
Client side: COllecting the data
  Percy CLI, language bindings (package)
Server side: Processing the data and generating the differences 

When you write percy.snapshot -> percy actually injects a javascript in the webpage, and it captures the DOM

Now before running the tests, we need to run the start the percy server locally 
using the exec command 
our language bindings will talk to this server 
So the screenshots will be sent to this server - and it will do asset discovery
It does that by using chromium browser in the background (headless)

Per snapshot configuration: width, min height, etc.
Percy will capture entirely from top to bottom - not just the viewport

Percy css: while taking a snapshot if I want to add anything / hide anything I can add this css in it

Scope: just want to take screenshot of a element - give the selector string 

discovery: 
so if I have image from a CDN, then percy will not have that downloaded in the asset discovery
so we can set, allowed hostnames - put the domain name, so percy will download that asset as well


Flows
Git flow - it works the same that is of the developers
So if its a feat branch, then it will be comparing the prev successful approved build on the feat branch
Visual git 
QAs will have t set a env variable
and then, whichever build they approve, that becomes the baseline branch for them so no need to merge or maintain anything




## Working
Percy addresses visual regression
- take screenshots
- compares new screenshots to the previous approved ones (baseline)
- Highlight the changes that are seen
- Integrate with CI/CD

## Important things:

Builds are also triggered on percy when we trigger test suites
Note: Builds expire according to the plan you are on. Builds on the free plan expire in 30 days, and other plans include 1 year of build history.

If project is connect to a github repo then it also has metadata and a pull request

### Visual Approval
In Percy, you can approve individual snapshots with visual changes, entire snapshot groups, or even whole builds. 
f you’ve integrated your source code, approving a build automatically updates your pull request or commit status to “All visual changes have been approved”.
Your build is auto-approved by default on all changes on the main branch. You can customize which branches get auto-approved in your project settings.

Only one snapshot needs to be marked as “Changes requested” for the entire build status to be updated to match.
Changes previously requested on snapshots are carried forward to subsequent builds as long as the diff matches the original snapshot comparison. When the diff no longer matches, it’s reverted to “Unreviewed”.

## Running Percy

### 1. No code or scripts
Can be done via CLI
Can take snapshots of the dom, sitemap, directories, lists, pages

Install the CLI
npm install --save-dev @percy/cli

You can specify a snapshots file, a static directory, or the URL of a sitemap that you wish to capture snapshots of.
percy snapshot [options] <file|dir|sitemap>


Now have a yaml file that has the urls of which we want snapshots
- http://localhost:8080
- http://localhost:8080/two

percy snapshot snapshots.yml

percy snapshot sitemap.xml


If we have json then
Customize snapshot behavior
[{
  "name": "Snapshot one",
  "url": "http://localhost:8080",
  "waitForTimeout": 1000
}, {
  "name": "Snapshot two",
  "url": "http://localhost:8080/two",
  "waitForSelector": ".some-element"
}]

Command is percy percy snapshot snapshots.json


Execute command: to interact with the browser elements when taking screenshots
With the snapshot command, you can interact with the page by providing an execute option. This can be any valid JavaScript you run inside of a browser.

The execute option can also accept an object with these keys:

afterNavigation - called after the page navigates to the given URL
beforeResize - called before the page viewport is resized to one of the passed width(s)
afterResize - called after the page viewport is resized to one of the passed width(s)
beforeSnapshot - called before Percy captures a snapshot

Eg:
- name: Execute Example
  url: http://localhost:8080/example
  execute:
    afterNavigation:
    beforeResize:
    afterResize:
    beforeSnapshot:


Can be also done in js 
Eg of js:
module.exports = [{
  name: 'My form',
  url: 'http://localhost:8080/form',
  waitForSelector: '.form-loaded',
  execute() {
    document.querySelector('.name').value = 'Name Namerson';
    document.querySelector('.email').value = 'email@domain.com';
  },
  additionalSnapshots: [{
    suffix: ' - submitting',
    execute() {
      document.querySelector('.submit').click();
    }
  }, {
    suffix: ' - after submit',
    waitForSelector: '.form-submitted'
  }]
}]


Eg of yaml file for configuring percy:
base-url: https://example.com
exclude:
  - /page/(\d+)
references:
  dismiss-cookie-banner: &dismiss-cookie-banner |
    document.querySelector('.cookie-banner .dismiss').click();
snapshots:
  - url: /foo
    execute: *dismiss-cookie-banner
  - url: /foo
    name: "/foo - with cookie banner"
  - url: /bar
    execute: *dismiss-cookie-banner


if we have a folder then we can ask it to scan its pages as well
eg: percy snapshot ./public

When providing a sitemap URL, the document must be an XML document. You can use --include and --exclude flags to filter snapshots. The Percy configuration file also accepts options.

Sitemaps can contain a lot of URLs, so its best to always start with the --dry-run flag while fine tuning the include and exclude options.

Command:
Copy
percy snapshot https://percy.io/sitemap.xml --dry-run


Screenshot a single element 
Sometimes capturing a full-page screenshot isn’t necessary. For example, if there are dynamic parts of the page that you don’t need to test or are only interested in a very specific region to test. For these cases, you can pass a scope snapshot option and Percy will only capture the scoped element on the given widths. T

```yaml
version: 2
snapshot:
	scope: '.selector'
``` 

if it is scrlling then
```yaml
version: 2
snapshot:
	scope: '.selector'
	scope-options:
	  scroll: true
```


to integrate percy with our test suite
in the yaml file just ensure upating the percy to true
and percy capture mode to auto

percy: true
percyCaptureMode: manual



## After running a build

Layout testing in Percy checks only the arrangement/position of UI elements (not their text/content) to ensure the page structure stays consistent.

## Build results
The diff highlighter highlights differences between snapshots.
Intelli Ignore and contextual diff help refine comparisons, and layout testing confirms UI elements align with design guidelines.

### Build Lifecycle
- creating, reviewing, approving, or discarding changes in a build

### Build Actions
Approve build - starts from the 1st build itself for the base build
Unapprove build – Revert the approval status of the build. This option is available when at least one snapshot is approved.
Reject build – Mark the build as rejected to prevent it from becoming the base build for future builds.
Delete build – Permanently remove the build and its associated snapshots.

### Snapshot actions
We can approve and unapprove the snapshots
Approve snapshot – Confirm the changes in the snapshot.
Unapprove snapshot – Revert the approval, marking the snapshot as unreviewed.

### Visual Git Options
Merge: Merge the changes from the current build into the baseline.
Unmerge – Undo the merge action, setting the previous merged build as the baseline.
Unmerge and unapprove build – Unmerge the build and change its status to unapproved.
Unmerge snapshot – Undo the merge action, setting the previous merged snapshot as the baseline.

## Approval Workflow

### Types of Approval
Approve build
Approve groups of matching visual changes
Approve individual snapshots

Individual screenshots (of a specific browser and resolution) cannot be approved
Individual snapshots are approved

Percy always compares first with the main branch, if there are differences, then it will check if the same differences were approved in the immediate previous build in same branch.


percy support CLI is used if you want to debug what was captured during that snapshot
download the dom from percy and show in browsser which you can inspect
