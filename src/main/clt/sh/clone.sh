
cd home
echo "base Dir"

cat branches.txt
while read line || [ -n "$line" ] ; do

  echo " $line"
  mkdir -p  ProjectDev-Base/$line
  cd  ProjectDev-Base/$line
  git clone -b $line --single-branch  https://github.com/virnect-corp/ProjectDev-Base.git
  ((i+=1))
done < branches.txt
