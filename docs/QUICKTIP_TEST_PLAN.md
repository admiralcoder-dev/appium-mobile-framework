# QuickTip Android — QA Test Plan

**Target:** QuickTip Android calculator, starting with a separate development/test installation.  
**Execution status:** Test cases below are planned unless individually marked executed in a future test report.  
**Framework:** Appium + UiAutomator2 + Java + TestNG + Maven.

## Scope

In scope: application launch, bill entry, preset and custom tips, per-person splitting, offline calculation, navigation/lifecycle, invalid input, and accessibility. Out of scope for this phase: payment processing, account creation, online APIs, and advanced tax/rounding features that QuickTip intentionally does not offer.

## Test design and expected results

| ID | Priority | Scenario | Test data | Expected result | Automation status |
| --- | --- | --- | --- | --- | --- |
| QT-001 | P0 | Launch app | Installed QuickTip | Main calculator appears, no crash | Existing smoke test scaffold; unverified on device |
| QT-002 | P0 | Preset 15% tip | Bill $10.00; 15%; split 1 | Tip $1.50; total $11.50; per person $11.50 | Existing conditional scaffold; selectors unverified |
| QT-003 | P0 | Split bill evenly | Bill $40.00; 20%; split 2 | Tip $8.00; total $48.00; per person $24.00 | Planned |
| QT-004 | P0 | Zero tip | Bill $25.00; 0%; split 1 | Tip $0.00; total $25.00 | Planned |
| QT-005 | P1 | Custom tip | Bill $80.00; 18%; split 4 | Tip $14.40; total $94.40; per person $23.60 | Planned |
| QT-006 | P1 | Decimal bill | Bill $12.50; 20%; split 1 | Tip $2.50; total $15.00 | Planned |
| QT-007 | P1 | Empty bill | Empty amount | No crash; appropriate default/validation per approved product spec | Planned; confirm expected UX |
| QT-008 | P1 | Negative bill | -10.00 | Invalid value rejected or handled safely; no negative payout | Planned; confirm expected UX |
| QT-009 | P1 | Zero/invalid split | 0 people or invalid text | Split count cannot become zero; no divide-by-zero | Planned |
| QT-010 | P1 | Offline use | Airplane mode | Previously installed app still calculates correctly | Planned |
| QT-011 | P1 | Background and resume | Enter bill, background, resume | No crash; state behavior matches agreed requirement | Planned |
| QT-012 | P1 | Status/navigation bars | Android device with gesture navigation | Controls remain visible and usable | Planned |
| QT-013 | P2 | Accessibility labels | TalkBack/accessibility inspection | Core controls have usable labels and focus order | Planned |
| QT-014 | P2 | Larger text / display | Increased font/display scale | Critical calculator controls remain accessible | Planned |

**Rounding note:** Confirm QuickTip's intended two-decimal display and splitting policy before asserting cases with fractional cents. Do not invent a policy for how residual cents are allocated.

## Test case format

Each implemented test should include:
- **ID and title**
- **Preconditions:** app version, package, device/Android version, starting state
- **Steps and data**
- **Expected result**
- **Actual result:** pass, fail, blocked, or skipped
- **Evidence:** report/screenshot/log path, when available
- **Defect link:** GitHub issue if a product bug is found

## Initial execution protocol

1. Confirm `adb devices -l` shows the real phone as `device`.
2. Check Java, Maven, Node, and Appium versions; pin compatible driver versions.
3. Confirm installed QuickTip package/activity or provide a separate test APK.
4. Start Appium and run only QT-001.
5. Inspect the accessibility hierarchy and verify selectors before QT-002.
6. Record actual test outcomes; never report skipped or unexecuted scenarios as passed.

## Exit criteria for first portfolio milestone

- One real-device launch smoke test executed with recorded outcome.
- At least one calculation test executed with verified selectors.
- Reproducible setup instructions and anonymized evidence.
- No sensitive credentials, keystores, personal data, or production signing material in the repository.
