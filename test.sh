rm -Rf bin/*
#javac test/RentServiceTest.java -d bin/
javac src/*.java -d bin/
cd bin
java org.junit.runner.JUnitCore test.RentServiceTest
