#!/bin/bash

#Specify application keyword. If you do not specify a keyword, use the script name.
KEYWORD=""
[[ $KEYWORD = "" ]] && KEYWORD=$(basename $0 .sh)

#JAVA Memory config
mem_pct=75
mem_kB=$(( `cat /proc/meminfo |grep MemTotal|awk '{print $2}'` ))
mem_MB=$(( $mem_kB*$mem_pct/100/1024 ))
export JAVA_OPTS="-Xms${mem_MB}m -Xmx${mem_MB}m"

#Query App process number
QueryTPID()
{
TPID=$(ps -ef|grep $KEYWORD|grep java|grep -v grep|awk '{print $2}'|tr "\n" " ")
}


#This script program must be in the application bin directory;otherwise exit.
PWDDIR="$(cd $( dirname "${BASH_SOURCE[0]}");pwd)"
if [ ! ${PWDDIR##*/} = "bin" ];then
	echo "This script program must be in the application bin directory,exit."
	exit 1
fi

#Config info
BASEDIR="`cd $PWDDIR/.. && pwd`"
EXEC="$(basename $0 .sh)"
CONF=""
PIDFILE="$BASEDIR/bin/$(basename $0 .sh).pid"


#Print config info
prt_cfg_info()
{
echo -e "App Name:$KEYWORD"
echo "BASEDIR:$BASEDIR"
echo "EXEC:$BASEDIR/bin/$EXEC"
echo "CONF:$CONF"
echo "PIDFILE:$PIDFILE"
echo "JAVA_OPTS=$JAVA_OPTS"
echo ""
}

#Check related directories and files
#[[ ! -d $BASEDIR/log ]] && mkdir -p $BASEDIR/log
#if [ !  -d $BASEDIR/log ];then
#	echo "Directory:$BASEDIR/log/ not exist,exit."
#	exit 1
#fi

if [ ! -x $BASEDIR/bin/$EXEC ];then
	echo "App $EXEC not exist or not execute file."
	exit 1
fi

#main script
case "$1" in
    start)
	prt_cfg_info
	QueryTPID
        if [ -n "$TPID" ];then
            echo "$KEYWORD is already running(pid:$TPID)"
        else
            echo -e "Starting $KEYWORD :\c"
            cd $BASEDIR
	    nohup bin/$EXEC > /dev/null 2>&1 &
	    sleep 1
	    QueryTPID
	    if [ -n "$TPID" ];then
		echo  "started (pid:$TPID)"
		echo "$TPID" > $PIDFILE
	    else
	    	echo "failed"	
	    fi
        fi
        ;;
    start-debug)
	prt_cfg_info
	QueryTPID
	if [ -n "$TPID" ];then
	    echo "$KEYWORD is already running(pid:$TPID)"
	else
	    cd $BASEDIR
	    cat /dev/null>nohup.out
	    nohup bin/$EXEC 2>&1 &
	    sleep 3
	    cat nohup.out
	fi    
	;;
    stop)
	QueryTPID
        if [ -z "$TPID" ];then
            echo "$KEYWORD is stopped"
        else
            echo "$KEYWORD (pid:$TPID) stopping ..."
            /bin/kill -9 $TPID
        fi
	[[ -f $PIDFILE ]] && rm -f $PIDFILE
        ;;
    status)
	prt_cfg_info
	QueryTPID
        if [ -z "$TPID" ];then
            echo "$KEYWORD is stopped"
        else
            echo "$KEYWORD (pid:$TPID) is running..."
        fi
        ;;
    restart)
        $0 stop
        sleep 2
        $0 start
        ;;
	version)
        echo "version:"$(cat $BASEDIR/VERSION.txt|awk 'BEGIN{FS="-"}{for(i=3;i<NF;i++) printf $i""FS""};{print $NF}')
        ;;
    *)
	echo $"Usage: $0 {start|start-debug|stop|restart|status}"
        ;;
esac

