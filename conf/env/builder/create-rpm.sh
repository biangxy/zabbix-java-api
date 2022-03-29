#!/bin/bash

version=$(cat VERSION.txt)

isSnapshot=false

if [[ $version == *SNAPSHOT ]]; 
then 
    version=$(echo ${version%-*})
    echo $version
    isSnapshot=true
fi

project_name=$(echo ${version%-*})
build_target=$project_name
release_version=$(echo ${version##*-})

cur_date=$(date +"%Y%m%d")

tar zcf $project_name-$release_version-$cur_date.tar.gz $project_name
echo "$project_name-$release_version-$cur_date.tar.gz"
mv -f $project_name-$release_version-$cur_date.tar.gz $1/SOURCES/
echo $1
rpmbuild --target noarch --define "build_target $build_target" --define "project_name $project_name" --define "release_version $release_version"  --define "cur_date $cur_date" -bb $project_name.spec

if [ "$isSnapshot" = true ]; 
then
    mv $1/RPMS/noarch/$project_name-$release_version-$cur_date.noarch.rpm $1/RPMS/noarch/$project_name-$release_version-SNAPSHOT.noarch.rpm
else
    mv $1/RPMS/noarch/$project_name-$release_version-$cur_date.noarch.rpm $1/RPMS/noarch/$project_name-$release_version.noarch.rpm
fi
