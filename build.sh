#!/bin/bash
SCRIPTDIR="$(dirname $(readlink -f $0))"
TARGETDIR=$SCRIPTDIR/target
mkdir $TARGETDIR
PACKAGE=$1
PACKAGEDIR=$(echo $PACKAGE | tr . /)
ARTIFACTNAME=$(basename $PACKAGEDIR)
PACKAGEDIR=$(echo $PACKAGEDIR | tr '[A-Z]' '[a-z]')
ARTIFACT=$TARGETDIR/$ARTIFACTNAME.zip
SOURCEDIR=$SCRIPTDIR/src/main/java/$PACKAGEDIR
cd $SOURCEDIR
ARTIFACTTARGET=$TARGETDIR/$ARTIFACTNAME
rm -rf $ARTIFACTTARGET
rm $ARTIFACT
mkdir $ARTIFACTTARGET
for f in *; do
	grep -v "\(package\|import com\.coursera\.algorithms\)" $f > $ARTIFACTTARGET/$f	
done
cd $ARTIFACTTARGET
zip $ARTIFACT *


