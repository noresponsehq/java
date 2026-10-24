# Changelog

## 6.0.1

* `sdk.accounts.listChanged()`: **Added**
* `sdk.accounts.recordEnforcement()`: **Added**
* `sdk.assessments.create()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.assessments.get()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.assessments.delete()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.assessments.recordOutcome()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.assessments.query()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.accounts.get()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.accounts.delete()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.accounts.listAssessments()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.keyUsage.record()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.keys.rotate()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.integrations.create()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.integrations.list()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.integrations.delete()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.integrations.rotate()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.list()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.create()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.delete()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.rotateSigningKey()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.sendTestEvent()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.listDeliveries()`:  `error.code.enum(riskVersionAhead)` **Added**
* `sdk.webhookEndpoints.replayDelivery()`:  `error.code.enum(riskVersionAhead)` **Added**

## 6.0.0

No changes detected.

## 5.1.1

* `sdk.assessments.create()`:  `response.account.riskVersion` **Added**
* `sdk.assessments.get()`:  `response.account.riskVersion` **Added**
* `sdk.accounts.get()`:  `response.riskVersion` **Added**

## 5.1.0

* `sdk.keys.rotate()`:  `response.scopes[].enum(delete)` **Added**

## 5.0.1

No changes detected.

## 5.0.0

* `sdk.assessments.create()`: 
  *  `request.body.flow` **Changed** (Breaking ⚠️)
  *  `response` **Changed** (Breaking ⚠️)
  *  `error.code` **Changed** (Breaking ⚠️)
* `sdk.assessments.get()`: 
  *  `response` **Changed** (Breaking ⚠️)
  *  `error.code` **Changed** (Breaking ⚠️)
* `sdk.assessments.delete()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.assessments.recordOutcome()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.assessments.query()`: 
  *  `response.items[].risk` **Changed** (Breaking ⚠️)
  *  `error.code` **Changed** (Breaking ⚠️)
* `sdk.accounts.get()`: 
  *  `response` **Changed** (Breaking ⚠️)
  *  `error.code` **Changed** (Breaking ⚠️)
* `sdk.accounts.delete()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.accounts.listAssessments()`: 
  *  `response.items[].risk` **Changed** (Breaking ⚠️)
  *  `error.code` **Changed** (Breaking ⚠️)
* `sdk.keyUsage.record()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.keys.rotate()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.integrations.create()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.integrations.list()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.integrations.delete()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.integrations.rotate()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.list()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.create()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.delete()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.rotateSigningKey()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.sendTestEvent()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.listDeliveries()`:  `error.code` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.replayDelivery()`:  `error.code` **Changed** (Breaking ⚠️)

## 4.0.1

No changes detected.

## 4.0.0

* `sdk.webhookEndpoints.listDeliveries()`:  `response.items[].eventType` **Changed** (Breaking ⚠️)
* `sdk.webhookEndpoints.replayDelivery()`:  `response.eventType` **Changed** (Breaking ⚠️)

## 3.0.9

No changes detected.

## 3.0.8

No changes detected.

## 3.0.7

No changes detected.

## 3.0.6

No changes detected.

## 3.0.5

No changes detected.

## 3.0.4

* `sdk.keyUsage.record()`: **Added**
* `sdk.assessments.create()`:  `response.account.risk.reasons[].code.enum(sharedApiKey)` **Added**
* `sdk.assessments.get()`:  `response.account.risk.reasons[].code.enum(sharedApiKey)` **Added**
* `sdk.assessments.query()`:  `response.items[].risk.reasons[].code.enum(sharedApiKey)` **Added**
* `sdk.accounts.get()`:  `response.risk.reasons[].code.enum(sharedApiKey)` **Added**
* `sdk.accounts.listAssessments()`:  `response.items[].risk.reasons[].code.enum(sharedApiKey)` **Added**

## 3.0.3

No changes detected.

## 3.0.2

No changes detected.

## 3.0.1

No changes detected.

## 3.0.0

* `sdk.assessments.create()`: 
  *  `request.body` **Changed** (Breaking ⚠️)
  *  `response` **Changed** (Breaking ⚠️)
* `sdk.assessments.get()`:  `response` **Changed** (Breaking ⚠️)

## 2.1.15

* `sdk.assessments.delete()`: **Added**

## 2.1.14

No changes detected.

## 2.1.13

No changes detected.

## 2.1.12

No changes detected.

## 2.1.11

* `sdk.accounts.delete()`: **Added**

## 2.1.10

* `sdk.assessments.create()`:  `request.body` **Changed**

## 2.1.9

No changes detected.

## 2.1.8

* `sdk.webhookEndpoints.listDeliveries()`: **Added**
* `sdk.webhookEndpoints.replayDelivery()`: **Added**

## 2.1.7

No changes detected.

## 2.1.6

No changes detected.

## 2.1.5

* `sdk.assessments.create()`:  `response.risk.reasons[].code.enum(testScenario)` **Added**
* `sdk.assessments.get()`:  `response.risk.reasons[].code.enum(testScenario)` **Added**
* `sdk.assessments.query()`:  `response.items[].risk.reasons[].code.enum(testScenario)` **Added**
* `sdk.accounts.get()`:  `response.risk.reasons[].code.enum(testScenario)` **Added**
* `sdk.accounts.listAssessments()`:  `response.items[].risk.reasons[].code.enum(testScenario)` **Added**

## 2.1.4

No changes detected.

## 2.1.3

No changes detected.

## 2.1.2

* `sdk.accounts.get()`: **Added**
* `sdk.accounts.listAssessments()`: **Added**

## 2.1.1

No changes detected.

## 2.1.0

* `sdk.assessments.create()`:  `response.authContext` **Added**
* `sdk.assessments.get()`:  `response.authContext` **Added**
* `sdk.assessments.query()`:  `response.items[].authContext` **Added**

## 2.0.19

* `sdk.webhookEndpoints.list()`: **Added**
* `sdk.webhookEndpoints.create()`: **Added**
* `sdk.webhookEndpoints.delete()`: **Added**
* `sdk.webhookEndpoints.rotateSigningKey()`: **Added**
* `sdk.webhookEndpoints.sendTestEvent()`: **Added**
* `sdk.assessments.create()`:  `error.code` **Changed**
* `sdk.assessments.get()`:  `error.code` **Changed**
* `sdk.assessments.recordOutcome()`:  `error.code` **Changed**
* `sdk.assessments.query()`:  `error.code` **Changed**
* `sdk.keys.rotate()`:  `error.code` **Changed**
* `sdk.integrations.create()`:  `error.code` **Changed**
* `sdk.integrations.list()`:  `error.code` **Changed**
* `sdk.integrations.delete()`:  `error.code` **Changed**
* `sdk.integrations.rotate()`:  `error.code` **Changed**

## 2.0.18

* `sdk.integrations.create()`: **Added**
* `sdk.integrations.list()`: **Added**
* `sdk.integrations.delete()`: **Added**
* `sdk.integrations.rotate()`: **Added**
* `sdk.assessments.create()`:  `error.code.enum(tooManyIntegrations)` **Added**
* `sdk.assessments.get()`:  `error.code.enum(tooManyIntegrations)` **Added**
* `sdk.assessments.recordOutcome()`:  `error.code.enum(tooManyIntegrations)` **Added**
* `sdk.assessments.query()`:  `error.code.enum(tooManyIntegrations)` **Added**
* `sdk.keys.rotate()`: 
  *  `response.scopes[].enum(integrations)` **Added**
  *  `error.code.enum(tooManyIntegrations)` **Added**

## 2.0.17

* `sdk.assessments.create()`: 
  *  `request.body.issuedCredential` **Added**
  *  `response.risk` **Changed**
* `sdk.assessments.get()`:  `response.risk` **Changed**
* `sdk.assessments.query()`:  `response.items[].risk` **Changed**

## 2.0.16

No changes detected.

## 2.0.15

* `sdk.assessments.create()`: 
  *  `request.body.authContext` **Changed**

## 2.0.14

No changes detected.

## 2.0.13

No changes detected.

## 2.0.12

No changes detected.

## 2.0.11

No changes detected.

## 2.0.10

No changes detected.

## 2.0.9

No changes detected.

## 2.0.8

No changes detected.

## 2.0.7

No changes detected.

## 2.0.6

No changes detected.

## 2.0.5

No changes detected.

## 2.0.4

No changes detected.

## 2.0.3

No changes detected.

## 2.0.2

API changes since the previous release are not listed: no earlier document was recorded.
