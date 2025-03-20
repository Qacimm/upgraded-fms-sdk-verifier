You're probably here to test whether the upgraded fms sdk is working as expected and returning the correct values in your service, unless you're stalking me. In any case, please checkout to your branch (same as service name)

The feature toggles used in your service is divided into files `active_vars.txt` which contains feature toggles invovked using `isActive` and `active_context_vars.txt` which contains feature toggles invovked using `isActiveForContextId` in your codebase.

Main Java service uses the upgraded FMS sdk to invovke all feature toggles in the same manner as they're called in the codebase (context for all isActiveForContextId feature toggles is 100L, you can change it).

`fms.sh` script fetch all feature toggle values from FMS backend

if you run `test.sh`, it will first fetch the feature toggles using sdk and then by hitting FMS, in the end it will take the diff of both and you can compare the results (final.diff).

Note: test.sh only compares the `isActive` feature toggles, we have to verify the values of `isActiveForContextId` manually, there are not too many of them. I have personally tested them and all are returning correct values
