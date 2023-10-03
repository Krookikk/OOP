javac -d ./out ./src/main/java/org/example/Main.java ./src/main/java/org/example/Heapsort.java
cd ./out
java -classpath . org.example.Main
cd ..
javadoc ./javadoc ./src/main/java/org/example/Main.java ./src/main/java/org/example/Heapsort.java
pause