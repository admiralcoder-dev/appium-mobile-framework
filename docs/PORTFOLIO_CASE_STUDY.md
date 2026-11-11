# AdmiralCoder Portfolio Case Study — Appium Mobile Automation

## Project title
**Appium Mobile QA Automation | QuickTip Android**

## Short website description

An Android mobile QA automation framework targeting QuickTip, a published offline tip calculator. The project uses Java, Appium, UiAutomator2, TestNG, Maven, and GitHub Actions to organize reusable screen objects, device configuration, test scenarios, and reporting. The roadmap includes real-device smoke testing, calculation validation, bill splitting, offline resilience, and accessibility checks.

**Current status:** Framework scaffold and configuration-level CI exist. Real-device execution and functional regression results are not yet verified or published.

## QA challenge

A mobile calculator must provide reliable arithmetic, consistent input handling, and a usable interface across Android devices. QuickTip is a Capacitor hybrid app, so test automation must account for Android-native accessibility and potential WebView context differences.

## QA approach

1. Design a maintainable test architecture with separate configuration, driver, screen, and assertion layers.
2. Run independent framework tests in CI without claiming they validate the Android application.
3. Connect a physical Android phone over ADB and establish a repeatable Appium session.
4. Verify actual element locators, then automate high-value calculation and split-bill scenarios.
5. Collect execution reports, failure screenshots, and GitHub issues for traceability.
6. Extend coverage to offline operation, lifecycle, accessibility, and device variations.

## Stack

- **Language:** Java 21
- **Mobile automation:** Appium + UiAutomator2
- **Test runner:** TestNG
- **Build/dependencies:** Maven
- **Source control:** GitHub
- **CI:** GitHub Actions (currently framework-only checks)
- **Test device:** Physical Android phone (planned validation)
- **Application under test:** QuickTip Android (Capacitor hybrid)

## Demonstrable engineering practices

- Screen Object Model
- Environment-driven capabilities
- Driver lifecycle management
- Explicit waits and failure screenshots
- Separation of CI configuration checks from real-device functional tests
- Traceable test case IDs, expected results, and defect evidence
- Production-safe development/test separation

## Evidence to add after execution

- [ ] First real-device Appium run with device/version metadata
- [ ] Screenshot of an actual passing calculation test
- [ ] TestNG/Surefire result summary with pass/fail/skip counts
- [ ] Example defect report or negative test with reproducible steps
- [ ] CI workflow screenshot correctly labeled framework checks
- [ ] Release regression report for QuickTip 1.1

## Links

- [Framework source code](https://github.com/admiralcoder-dev/appium-mobile-framework)
- [AdmiralCoder](https://admiralcoder.com)

**Suggested website label:** *In progress — framework established; device validation underway.*

**Do not claim:** full automated coverage, device test pass rates, production app defects found, or emulator CI success until supported by real execution evidence.
