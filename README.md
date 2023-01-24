# GloveBox Android Challenge

## Instructions

Fork this repo and add the following functionality to this Android App. Once you are finished, send us a link to your fork.

## Objectives

1. Inside `com.gloveboxapp.androidchallenge.data` there exists two json files, `policies.json` and `policy_types.json`. Create two methods `getPolicies` and `getPolicyTypes` to fetch and parse these two json files into Kotlin data classes to be used within the app.
2. On the home screen `HomeFragment`, load the policies from `getPolicies` / `policies.json` in a list (RecyclerView).
3. These policies should get stored in our singleton repository class `GloveBoxRepository.kt` and retrieved by `HomeViewModel`.
4. Group the records in the policies array by `carrierID` then render one list for each carrier - with each list containing the policies associated with that carrier.
5. Style with Android material design.
6. Place an edit button on each policy. When clicked show a policy edit form with the following:

    - Select input with options populated from the `policy_types.json`. The default option selected should reflect the current policy type.
    - Save button that when pressed persists the policy type update into the policies redux store and hides the edit form.

## Requirements

- Both the policies array retrieved from `getPolicies` and the policyTypes array from `getPolicyTypes` should be stored in `GloveBoxRepository.kt`.
- The update policy type form, when submitted should update the policy type for the appropriate policy inside the redux store
- Both the `getPolicies` and `getPolicyTypes` methods should be called only once per application load
- The UI should look good in both with elements wrapping when necessary. The design should be simple and modern with space around each element.