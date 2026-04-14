# BrowserStack Local Testing Reference

Documentation and command reference for establishing secure tunnels and utilizing BrowserStack Local testing tools.

## Basic Commands

**Check Installation**
```bash
BrowserStackLocal --version
```

**Start Binary**
```bash
BrowserStackLocal --key YOUR_ACCESS_KEY
```

**Binary Dashboard**
* **URL:** `http://localhost:45454`
* **App Invocation:** `http://localhost:45454/?action=app-invocation`

---

## Live Testing Tools

**Status Check**
* Verify connection: `http://localhost:45691/check`

---

## Debugging & Admin

| Tool | Purpose | URL |
| :--- | :--- | :--- |
| **Hyperdrive** | Local connection health | [hyperdrive.bsstag.com](https://hyperdrive.bsstag.com/local/) |
| **Central Admin** | Force repeater/Local ops | [local.browserstack.com](https://local.browserstack.com/admin/local_ops_force_repeater) |
| **Network Panel** | Outbound IP whitelisting | [browserstack.com/accounts/network-panel](https://www.browserstack.com/accounts/network-panel) |

---

## Quick Tips
* Ensure the binary has executable permissions (`chmod +x BrowserStackLocal`).
* The `--key` is mandatory for establishing the secure tunnel.

---

## Additional Resources

*   **Local Documentation:** https://browserstack.atlassian.net/wiki/spaces/CE/pages/3468329990/All+About+Local+Testing#Architecture
*   **Local Onboarding Assignments:** https://browserstack.atlassian.net/wiki/spaces/CE/pages/3639050389/Local+Onboarding