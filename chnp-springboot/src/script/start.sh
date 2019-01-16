#! /bin/sh
# author: chngzhen@outlook.com

. ./utils.sh

start() {
  process=`ps aux | grep $1 | grep -v grep`
  if [ "" == "$process" ]
    then
      jarPath=""
      jarFiles=`find $2 -name "*.jar"`
      for jarFile in $jarFiles
        do
          if [ -f $jarFile ]
          then
            jarPath=$jarPath$jarFile":"
          fi
        done
      if [ ! -n $jarPath ]
        then
          nohup java -Xms1024m -Xmx4096m -cp $jarPath$3:. $4 >/dev/null &
          precess=`ps aux | grep $1 | grep -v grep`
          if [ "" == "$process" ]
            then
              log "The process is started successfully!"
              log 
          fi
      else
        log "There is no file with the suffix '.jar' in the directory '$2'"
      fi
  else
    log "The process containing '$1' has existed!"
    log "$process"
  fi
  exit 1
}


start $1 $2
