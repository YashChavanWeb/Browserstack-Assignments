## Resources

### BrowserStack Links
* **Data Centers:** [View Locations](https://www.browserstack.com/data-centers)
* **Live Product Onboarding:** [Confluence Docs](https://browserstack.atlassian.net/wiki/spaces/CE/pages/3953197230/BrowserStack+Live+Product+Onboarding+Page)

### Testing
* **Upload/Download UI:** `http://localhost:3000`

---

## External Resources

*   **Download Demonstrate:** https://the-internet.herokuapp.com/download
*   **All Live Features:** https://browserstack.atlassian.net/wiki/spaces/CE/pages/3956375637/Live+-+Talk+Track

## Node.js File Server

A lightweight Node.js server for uploading and downloading files. Built using pure Node.js APIs with **zero** NPM dependencies.

---

## Local Execution

### Prerequisites
* **Node.js** installed on your system.

### Setup & Run
1. **Get the project:** Clone the repo or download the ZIP.
2. **Start (Default Port 3000):**
   ```bash
   node server.js
   ```
3. **Start (Custom Port):**
   ```bash
   node server.js 4200
   ```
4. **Access UI:** Open `http://localhost:3000` in your browser.

---

## Docker Execution

### 1. Build Image
```bash
docker build -t fileserver:1.1 .
```

### 2. Run Container
Map a local folder to the container to persist files:
```bash
docker run -d -v $PWD/file-server:/app/download -p 3000:3000 --restart=always fileserver:1.1
```
*Note: Files are stored locally in `$PWD/file-server`.*

---

