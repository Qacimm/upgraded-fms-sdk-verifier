You're probably here to test whether the upgraded fms sdk is working as expected and returning the correct values in your service, unless you're stalking me. In any case, please checkout to your service's branch (same as service name)

The feature toggles used in your service is divided into files `active_vars.txt` containing feature toggles invovked using `isActive` and `active_context_vars.txt` containing feature toggles invovked using `isActiveForContextId` in your codebase.

Main Java service uses the upgraded FMS sdk to invovke all feature toggles in the same manner as they're called in the codebase, context for all isActiveForContextId feature toggles is 100L as all the enabled toggles in `trip_rating_service` have 100L as context.

`fms.sh` script fetch all feature toggle values from FMS backend

Run `test.sh`, it will first fetch the feature toggles using sdk and then by FMS backend, in the end it will take the diff of both and you can view the diff in respective files ( active_vars.diff and active_context_vars.diff)

