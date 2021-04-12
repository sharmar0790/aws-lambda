# AWS LAMBDA (Java)

![Architecture](https://github.com/sharmar0790/archietecture-images/blob/main/sample-java-basic.png)

The project source includes function code and supporting resources:
- `src/main` - A Java function.
- `src/test` - A unit test and helper classes.
- `template.yml` - An AWS CloudFormation template that creates an application.
- `pom.xml` - A Maven build file.
- `create-bucket.sh`, `deploy.sh`, etc. - Shell scripts that use the AWS CLI to deploy and manage the application.

Use the following instructions to deploy the sample application.

# Requirements
- Java 8
- Maven 3
- The Bash shell. 
- AWS CLI V2

If you use the AWS CLI v2, add the following to your [configuration file](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html) (`~/.aws/config`):

```
cli_binary_format=raw-in-base64-out
```

This setting enables the AWS CLI v2 to load JSON events from a file, matching the v1 behavior.

To create a new bucket for deployment artifacts, run `create-bucket.sh`.

    $ ./create-bucket.sh
    make_bucket: lambda-artifacts-a5e4xmplb5b22e0d

# Deploy
To deploy the application, run `deploy.sh`.

    $ ./deploy.sh
    BUILD SUCCESSFUL in 1s
    Successfully packaged artifacts and wrote output template to file out.yml.
    Waiting for changeset to be created..
    Successfully created/updated stack - aws-lambda

This script uses AWS CloudFormation to deploy the Lambda functions and an IAM role. If the AWS CloudFormation stack that contains the resources already exists, the script updates it with any changes to the template or function code.

You can also build the application with Maven. To use maven, add `mvn` to the command.

    $ ./deploy.sh mvn
    [INFO] Scanning for projects...
    [INFO] -----------------------< com.aws.lambda:aws-lambda >-----------------------
    [INFO] Building aws-lambda-function 1.0-SNAPSHOT
    [INFO] --------------------------------[ jar ]---------------------------------
    ...

# Test
To invoke the function, run `invoke.sh`.

    $ ./invoke.sh
    {
        "StatusCode": 200,
        "ExecutedVersion": "$LATEST"
    }
    "200 OK"

# Configure Handler Class

By default, the function uses a handler class named `Handler` that takes a map as input and returns a string. The project also includes handlers that use other input and output types. These are defined in the following files under src/main/java/example:

- `SampleHandle.java` – Takes a `String` as input.
- `HandleList.java` – Takes a `List<String>` as input.

To use a different handler, change the value of the Handler setting in the application template (`template.yml`). For example, to use the list handler:

    Properties:
      CodeUri: target/aws-lambda-1.0-SNAPSHOT.jar
      Handler: com.aws.lambda.HandleList

Deploy the change, and then use the invoke script to test the new configuration. For handlers, that don't take a JSON object as input, pass the type (`string`, `int` or `list`) as an argument to the invoke script.

    ./invoke.sh list
    {
        "StatusCode": 200,
        "ExecutedVersion": "$LATEST"
    }
    9979

# Cleanup
To delete the application, run `cleanup.sh`.

    $ ./cleanup.sh
