
import time
import json
import boto3

def lambda_handler(event, context):
    client = boto3.client("ec2")
    ssm = boto3.client("ssm")

    describeInstance = client.describe_instances()
    InstanceId = ['instance id']
    for i in describeInstance["Reservations"]:
        for instance in i["Instances"]:
            if instance["State"]["Name"] == "running":
                InstanceId.append(instance["InstanceId"])

    for instanceid in InstanceId:
        response = ssm.send_command(
            InstanceIds=[instanceid],
            DocumentName="AWS-RunShellScript",
            Parameters={
                "commands": ["sh /home/ec2-user/docker/deploy.sh"]
            },
        )

        command_id = response["Command"]["CommandId"]
        time.sleep(3)
        output = ssm.get_command_invocation(CommandId=command_id, InstanceId=instanceid)
        print(output)

    return {"statusCode": 200, "body": json.dumps("Success")}