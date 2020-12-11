# Phase 0

## Create local repositories and fetch information from the common repository

A **single** group member should perform the following steps:

1. Clone the group repo, located at <https://github.com/isel-leic-ls/1718-2-LI41D-Gxx>, to a local folder (which will be your local repo)
  * `git clone ...`
1. The previous execution will set the group repo as `origin`. 
  1. Set the common repo as `common`
     * `git remote add common https://github.com/isel-leic-ls/1718-2-common`
1. Get the state of the common repo
  * `git pull common master`
1. Solve any conflict
1. *Commit* any changes, if necessary
1. Publish the state of the local repository to the group repository
  * `git push origin master`
  * Performed once per group

## Correct errors and add tests

1. Create the local repository from the group repository
  * (the student that made the previous may skip this step!)
  * `git clone ....`
1. Execute the unit tests using Gradle and check that there is an error
1. Create a project in IntelliJ using the recommendations of the previous classes
1. Execute the same testes now using the IntelliJ IDE.
1. Correct the cause of the error and check that all tests are now correctly executed
1. Add more tests if necessary
1. Check, using the Gradle build script at the command line, that all the tests are executed with success
1. Publish all the changes (if any) to the local and group repository


## Database configuration and connectivity test

1. Create a database in the chosen DB Management system (i.e. SQLServer) on your machine. Add a table to represent *students*.
1. Make a set of tests to check that your system can read (i.e. `SELECT`), update (i.e. `INSERT` and `UPDATE`) and delete (i.e. `DELETE`) over the previous table, using the JDBC interface.
  * Assign different tests to each member of the group
1. Publish the changes to the group repo
1. Create a *tag* with the identifier 0.0.0 and publish it to the group repo
  * `git tag -a 0.0.0 -m "Version 0.0.0"` 
  * `git push origin 0.0.0`
  * Performed only once per group
 