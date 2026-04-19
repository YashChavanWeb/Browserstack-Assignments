# Percy Visual Testing – Automate Integration

Project Link:
[https://percy.io/f653eabd/web/automate-percy-test-afcef2fb](https://percy.io/f653eabd/web/automate-percy-test-afcef2fb)

---

## Overview

Percy is a visual testing tool used to detect and track visual bugs.

Functional testing focuses on verifying features, whereas visual testing ensures that the website's design appears as intended across different browsers, devices, and resolutions. Performing this manually is time-consuming.

Percy automates this process.

---

## AI Agent Features

### 1. Visual Review Agent

* Filters out unnecessary noise changes
* Focuses only on relevant visual differences

### 2. Text Format Validation

* Handles dynamic data such as dates, phone numbers, and IDs
* Allows defining format-based rules in plain English
* Reduces false positives (e.g., ignores daily date updates or random IDs while keeping tests green)

### 3. Visual Test Integration Agent

* Automates Percy setup
* Works similar to GitHub Copilot
* Powered by BrowserStack MCP

---

## Percy Architecture

Percy consists of two main components:

### Client Side

Responsible for collecting data:

* Percy CLI
* Language bindings (SDK/packages)

### Server Side

Responsible for processing:

* Generates visual differences
* Performs comparisons

---

## How Percy Works Internally

* When `percy.snapshot` is called, Percy injects JavaScript into the webpage
* It captures the DOM instead of just taking an image

Before running tests:

* A local Percy server must be started using the `exec` command
* Language bindings communicate with this server

Flow:

1. Screenshots are sent to the Percy server
2. Percy performs **asset discovery**
3. Uses a headless Chromium browser to load assets

---

## Snapshot Configuration

* Width
* Minimum height
* Full-page capture (top to bottom, not just viewport)

### Percy CSS

* Allows adding or hiding elements during snapshot capture

### Scope

* Capture only a specific element using a selector

### Asset Discovery Issue

* External assets (e.g., CDN images) may not be captured
* Solution: Configure **allowed hostnames** so Percy downloads them

---

## Percy Workflows

### Git Flow

* Works similar to developer workflows
* Feature branches compare against the last approved build in that branch

### Visual Git

* QA sets an environment variable
* Approved builds become the baseline automatically
* No need for manual merge or maintenance

---

## Core Functionality

Percy performs visual regression testing by:

* Taking screenshots
* Comparing with baseline images
* Highlighting differences
* Integrating with CI/CD pipelines

---

## Builds and Lifecycle

* Builds are triggered when test suites run
* Build retention depends on the plan:

  * Free plan: 30 days
  * Paid plans: up to 1 year

If integrated with GitHub:

* Builds include metadata
* Linked to pull requests

---

## Visual Approval

* Approvals can be done at:

  * Snapshot level
  * Snapshot group level
  * Entire build level

* Approving a build updates PR status (if integrated)

Additional rules:

* Main branch builds are auto-approved by default
* One "Changes requested" snapshot marks the entire build accordingly
* Previous decisions carry forward if diffs remain the same

---

## Running Percy

### 1. Using CLI (No Code Required)

Install:

```bash
npm install --save-dev @percy/cli
```

Basic usage:

```bash
percy snapshot <file|dir|sitemap>
```

---

### YAML Example

```yaml
- http://localhost:8080
- http://localhost:8080/two
```

Command:

```bash
percy snapshot snapshots.yml
```

---

### JSON Example

```json
[
  {
    "name": "Snapshot one",
    "url": "http://localhost:8080",
    "waitForTimeout": 1000
  },
  {
    "name": "Snapshot two",
    "url": "http://localhost:8080/two",
    "waitForSelector": ".some-element"
  }
]
```

Command:

```bash
percy snapshot snapshots.json
```

---

## Execute Command

Allows interaction with the page before snapshot:

Hooks:

* afterNavigation
* beforeResize
* afterResize
* beforeSnapshot

---

### JS Example

```js
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
}];
```

---

## Percy YAML Configuration Example

```yaml
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
```

---

## Directory and Sitemap Support

```bash
percy snapshot ./public
```

For sitemap:

```bash
percy snapshot https://percy.io/sitemap.xml --dry-run
```

---

## Scoped Screenshots

```yaml
version: 2
snapshot:
  scope: '.selector'
```

With scroll:

```yaml
version: 2
snapshot:
  scope: '.selector'
  scope-options:
    scroll: true
```

---

## Integration with Test Suite

```yaml
percy: true
percyCaptureMode: manual
```

---

## After Build Execution

* Layout testing checks UI structure (not content)
* Ensures alignment and positioning

---

## Build Results

* Diff highlighter shows changes
* Intelli Ignore reduces noise
* Contextual diff improves comparison accuracy

---

## Build Actions

* Approve build
* Unapprove build
* Reject build
* Delete build

---

## Snapshot Actions

* Approve snapshot
* Unapprove snapshot

---

## Visual Git Actions

* Merge
* Unmerge
* Unmerge and unapprove build
* Unmerge snapshot

---

## Approval Workflow

Types:

* Approve build
* Approve groups
* Approve individual snapshots

Notes:

* Individual screenshots (browser/resolution) cannot be approved
* Only snapshots are approved

Comparison logic:

* First compares with main branch
* If differences exist, checks previous approved build in the same branch

---

## Debugging

* `percy support` CLI helps debug snapshots
* Allows downloading DOM and inspecting it in the browser

---

## Types of Differences

* DOM differences
* CSS differences
* Position differences

---

## Synchronous Comparison Results

Allows real-time feedback:

```js
const response = await percyScreenshot(driver, name, { sync: true });

console.log(response["dashboard-urls"]["current-snapshot"]);
console.log(response.status);
```

---

## Figma Baseline Management

Reference:
[https://www.browserstack.com/docs/percy/visual-testing-workflows/figma/baseline-management](https://www.browserstack.com/docs/percy/visual-testing-workflows/figma/baseline-management)

---

## Pseudo-Classes Support

* Supports states like `:hover`, `:focus`, `:active`
* Can be triggered via:

  * JavaScript
  * Percy-specific CSS (`.percy-hover`)

---

If you want, I can next:

* Convert this into a **company-level polished README**
* Or add **diagrams/flowcharts for better understanding**
