var defaults = require("./wdio.conf.js");
var _ = require("lodash");

var overrides = {
    user: process.env.BROWSERSTACK_USERNAME,
    key: process.env.BROWSERSTACK_ACCESS_KEY,

    specs: ["./test/specs/e2e.spec.js"],
    services: [["browserstack"]],

    commonCapabilities: {
        "browserstack.debug": true,
        build:
            process.env.BROWSERSTACK_BUILD_NAME ||
            "browserstack-percy-appium-webdriverio" +
                " - " +
                new Date().getTime(),
        project: "browserstack-percy-appium-webdriverio",
    },

    capabilities: [
        {
            device: "Google Pixel 7",
            os_version: "13.0",
            app: "BStackAppAndroid",
            autoGrantPermissions: true,
            platformName: "Android",
        },
        {
            device: "Samsung Galaxy A51",
            os_version: "10.0",
            app: "BStackAppAndroid",
            autoGrantPermissions: true,
            platformName: "Android",
        },
        {
            device: "iPhone 13",
            platformName: "iOS",
            os_version: "15",
            gpsEnabled: "true",
            automationName: "XCUITest",
            app: "BStackAppIOS",
        },
        {
            device: "iPhone 14",
            platformName: "iOS",
            os_version: "16",
            gpsEnabled: "true",
            automationName: "XCUITest",
            app: "BStackAppIOS",
        },
    ],
};

const tmpConfig = _.defaultsDeep(overrides, defaults.config);

tmpConfig.capabilities.forEach((caps) => {
    for (const i in tmpConfig.commonCapabilities)
        caps[i] = caps[i] || tmpConfig.commonCapabilities[i];
});

exports.config = tmpConfig;
