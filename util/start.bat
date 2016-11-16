# Small utility to start the hsql server and its gui
start /b java -jar lib/hsqldb.jar
java -classpath lib/hsqldb.jar org.hsqldb.server.Server