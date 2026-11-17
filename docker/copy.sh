#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
#cp ../sql/antcloud_20240305.sql ./mysql/db
#cp ../sql/antcloud_nacos_20240423.sql ./mysql/db

# copy html
echo "begin copy html "
#cp -r ../antcloud-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy antcloud-gateway "
cp ../antcloud-gateway/target/antcloud-gateway.jar ./antcloud/gateway/jar

echo "begin copy antcloud-auth "
cp ../antcloud-auth/target/antcloud-auth.jar ./antcloud/auth/jar

echo "begin copy antcloud-monitor "
cp ../antcloud-monitor/target/antcloud-monitor.jar  ./antcloud/monitor/jar

echo "begin copy antcloud-system "
cp ../antcloud-modules/antcloud-system/target/antcloud-system.jar ./antcloud/modules/system/jar

echo "begin copy antcloud-job "
cp ../antcloud-modules/antcloud-job/target/antcloud-job.jar ./antcloud/modules/job/jar

echo "begin copy antcloud-gen "
cp ../antcloud-modules/antcloud-gen/target/antcloud-gen.jar ./antcloud/modules/gen/jar

echo "begin copy antcloud-project "
cp ../antcloud-modules/antcloud-project/target/antcloud-project.jar ./antcloud/modules/project/jar

echo "begin copy antcloud-workflow "
cp ../antcloud-modules/antcloud-workflow/target/antcloud-workflow.jar ./antcloud/modules/workflow/jar

