# Automate

For detailed setup instructions regarding the Cross-Browser Automation Agent, refer to the [Automate Docs](https://www.browserstack.com/docs/automate/selenium/set-up-test-env/cross-browser-automation-agent?fw-lang=java%2Ftestng).

## Core Integration Pointers

* **Preference Logic:** While your existing test suite defines driver initialization and capabilities, the **BrowserStack SDK** takes precedence once integrated.
* **Default Values:** If platforms or parallel execution counts are not explicitly defined, the system defaults to values set by the repeater.
* **Platform Mapping:** It is not possible to define which specific test runs on which platform via the configuration file. If platform-specific routing is required, it must be handled directly within the test logic.

## AI Agents and Self-Healing

BrowserStack provides several AI-driven agents to reduce flakiness and accelerate debugging.

### 1. Self-Healing Agent
* **Configuration:** Enable by setting `selfHeal: true`.
* **Functionality:** It stores the locators of interacted elements. If a locator changes in a subsequent run, the agent identifies the correct element to prevent test failure.
* **Requirement:** Requires at least one successful run to baseline element data.
* **Visibility:** Self-healing actions are logged in **yellow** within the text logs.

### 2. Failure Analysis Agent
* **RCA & Debugging:** Provides an AI-powered Root Cause Analysis (RCA) after a test failure.
* **Categorization:** Automatically categorizes the issue type and suggests required code fixes.
* **Integration:** Supports direct ticket creation from the BrowserStack UI based on the analysis.

### 3. Smart Test Selection
Uses AI to map code changes to relevant tests, ensuring only necessary tests are executed.

```yaml
testOrchestrationOptions:
  runSmartSelection:
    enabled: true
    mode: 'relevantFirst' # Options: 'relevantFirst' or 'relevantOnly'
    source: 
      - './app-repo1'
      - './app-repo2'
```
* **relevantFirst:** Runs impacted tests first, followed by the rest of the suite.
* **relevantOnly:** Runs only the tests impacted by the specific code changes.

### 4. Cross-Browser Automation Agent
* **AI Authoring:** Enable by setting `aiAuthoring: true`.
* **Capability:** Allows the use of natural language commands for test authoring. If this capability is not specified, natural language commands will not be functional.

---

## Test Orchestration and Optimization

The following strategies are available to optimize the test execution lifecycle:

| Strategy | Description |
| :--- | :--- |
| **Auto rerun** | Immediately retries failed tests for a configurable number of attempts. |
| **Fail fast** | Stops execution of the suite once a specific failure threshold is reached. |
| **Run failures only** | Executes only the tests that failed in the previous build. |
| **Prioritizing failures** | Prioritizes running previously failed tests at the start of the suite. |
| **Skip flaky/failing** | Skips tests based on historical failure or flakiness criteria. |
| **Smart Test Selection** | Runs tests likely to fail based on recent code changes. |

---

## Advanced Workflows and Integrations

* **BrowserStack Executor:** Use the executor within the code to handle complex workflows such as Apple Pay, file uploads, and downloads.
* **CI/CD:** Full support for Jenkins integration for automated execution.
* **Debugging:** Configure enhanced debugging options, including video capture capabilities for visual validation.
