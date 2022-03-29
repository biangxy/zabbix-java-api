#!/bin/bash
if [ ! `whoami` = root ] ;then
echo -e "Please execute this script with root."
exit 1
fi

#print title
clear
echo -e "\n\t Application auto start config menu"

script_select_menu()
{
#print script select menu
list=(`find $PWDDIR/*.sh|grep -v $0`)
echo -e "\nAll application script list:\n"
for(( i=0;i<${#list[@]};i++)) do echo -e "\t $i.  ${list[i]}"; done;
read -p "Select a script to setup auto start??Default:0 (0-$[i-1])" input
[[ $input = "" ]] && input=0
case $input in
	[0-$[i-1]])
		echo -e "Select: $input. ${list[$input]}"
		AppScript=${list[$input]}
		return 
		;;
   	*)
    		echo -e "Input error, Retry!"
		script_select_menu
    		;;
esac
} 

service_name_menu()
{
srvnamedef=$(basename $AppScript .sh) && srvnamedef=${srvnamedef:0:9}
read -p "Input auto service name(Default:$srvnamedef):" svrname
[[ $svrname = "" ]] && svrname=$srvnamedef
echo -e "Service name: $svrname"
[[ -e "/etc/init.d/$svrname" ]] && echo -e "/etc/init.d/$svrname exist,it will be deleted. "
}

app_user_menu()
{
usernamedef=${svrname##*-}
read -p "Input app execute user(Default:$usernamedef):" username
[[ $username = "" ]] && username=$usernamedef
echo -e "App execute user: $username"
}
change_owner_menu()
{

echo -e "The owner of the following directory will be modified to $username user."
echo -e "$APPDIR"
}

AreYouSure()
{
echo -e "\nConfig info:"
echo "1. Service name:$svrname"
echo "2. App script:$AppScript"
echo "3. App user:$username"
echo "4. App directory:$APPDIR"
echo ""
read -p "Are you sure??(yes|1~4|quit,Default:yes)" input
[[ $input = "" ]] && input="y"

case $input in
        y|yes|Y|YES)
                return
                ;;
	1)service_name_menu;;
	2)script_select_menu;;
	3)app_user_menu;;
	4)change_owner_menu;;
        q|quit|Q|QUIT)
                echo -e "\nQuit."
                exit 5
                ;; 
        *)
                echo -e "Input error, Retry!"
                ;;
esac
AreYouSure
}


#main

PWDDIR="$(cd $( dirname "${BASH_SOURCE[0]}");pwd)"
APPDIR="`cd $PWDDIR/..;pwd`"
cd $PWDDIR
script_select_menu
service_name_menu
app_user_menu
change_owner_menu
AreYouSure

echo "Result info:"
cat >/etc/init.d/$svrname <<EOF
#!/bin/bash
#chkconfig: 2345 90 10
su - $username -c "$AppScript \$@"
EOF

chmod +x /etc/init.d/$svrname
echo -e "\n /etc/init.d/$svrname file info:"
ls -lrt /etc/init.d/|tail -n 3
echo "============================================"
echo -e "/etc/init.d/$svrname:\n"
cat /etc/init.d/$svrname
echo "============================================"
chkconfig $svrname on
chkconfig --list $svrname
echo ""
[[ ! `id $username ` ]] && useradd $username
id $username
echo ""
chown -R $username:$username $APPDIR
ls -l $APPDIR/..
echo -e "\nFinished."










