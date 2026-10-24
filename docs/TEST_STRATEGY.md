# Mobile QA strategy

## Purpose

Establish a maintainable Appium 2 / Java / TestNG framework for QuickTip Android without falsely reporting app coverage before an APK is available.

## Validation tiers

1. **Framework CI** (default): compile Java and execute configuration/capability tests. No emulator needed. The TestNG group `mobile` is excluded.
2. **Device smoke** (manual opt-in): start Appium UiAutomator2 session with QuickTip APK or installed package; confirm UI hierarchy is available.
3. **Functional mobile** (manual opt-in): use verified accessibility IDs to enter bill, select tip, and assert displayed total. Skips if identifiers have not been inspected.

## Design

- Environment-based device and app configuration.
- Screen Object Model for reusable UI interactions.
- ThreadLocal driver session with teardown.
- TestNG failure listener writes screenshots.
- Explicit waits for mobile UI.
- No secrets, customer information, or real child data.

## Future test matrix

- Android versions and form factors.
- Tip percentages, custom percentage and currency amounts.
- Split-bill rounding and grouped payments.
- Currency formatting and localization (as supported by QuickTip version).
- Rotation, background/resume, offline use, and accessibility.

## Limitations

No APK, emulator, Appium server, or real mobile test execution is part of default CI. Framework checks passing cannot be interpreted as QuickTip application testing.
