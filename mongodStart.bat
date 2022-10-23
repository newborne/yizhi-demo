@echo off
echo 启动MongoDB......
::进入d盘
D:
:: cd \Program Files\MongoDB\bin
cd D:\software\mongodb-win32-x86_64-windows-4.4.16\bin
mongod --dbpath "D:\MongoDB\data\db"
:: 指定端口
:: mongod --dbpath "D:\Program Files\MongoDB\data" --port 10001
:: {"t":{"$date":"2022-09-24T08:42:29.401+08:00"},"s":"E",  "c":"STORAGE",  "id":20557,   "ctx":"initandlisten","msg":"DBException in initAndListen, terminating","attr":{"error":"NonExistentPath: Data directory D:\\MongoDB\\data\\db not found. Create the missing directory or specify another path using (1) the --dbpath command line option, or (2) by adding the 'storage.dbPath' option in the configuration file."}}
