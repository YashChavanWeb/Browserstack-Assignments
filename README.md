# BrowserStack Assignments

This repository serves as a centralized collection of assignments and reference implementations for BrowserStack's suite of testing tools. It includes modules for live testing, local connectivity, and comprehensive test management.

## Project Structure

<!-- START_STRUCTURE -->
The project is organized into the following specialized modules:

### [Node.js File Server](./Live)
A lightweight Node.js server for uploading and downloading files. Built using pure Node.js APIs with **zero** NPM dependencies.
- [Documentation](./Live/Readme.md)

### [BrowserStack Local Testing Reference](./Local-Testing)
Documentation and command reference for establishing secure tunnels and utilizing BrowserStack Local testing tools.
- [Documentation](./Local-Testing/Readme.md)

### [BrowserStack Test Management](./Test-Management)
A comprehensive platform for managing, authoring, and executing test cases with AI-powered insights and automated integrations.
- [Documentation](./Test-Management/Readme.md)

#### [testng-browserstack](./Test-Management/testng-curl)
- [Documentation](./Test-Management/testng-curl/README.md)

#### [TestNG to Test Management](./Test-Management/testng-to-tm)
- [Documentation](./Test-Management/testng-to-tm/README.md)

<!-- END_STRUCTURE -->

## Automated Updates
This repository utilizes a CI/CD pipeline to ensure the project structure and documentation links in this README remain synchronized with the underlying directory structure. Any additions of new modules or updates to internal README files will trigger an automatic documentation refresh.

---

For detailed instructions on individual modules, please refer to the specific documentation linked above.
