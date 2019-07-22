#!/bin/bash
ROOT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )"/.. && pwd )"
REPO_DIR=$ROOT_DIR/.repo

rm -rf $REPO_DIR
mkdir $REPO_DIR
# Clone modelserver-p2 and switch to new skeleton branch
cd $REPO_DIR || exit
git clone  https://github.com/eclipsesource/modelserver-p2.git
cd modelserver-p2 || exit

git checkout plugin_skeleton
git checkout -b new_deploy

# (Re)generate plugis
cd scripts || exit
. ./functions.sh
. ./generate.sh

rm -rf $REPO_DIR
