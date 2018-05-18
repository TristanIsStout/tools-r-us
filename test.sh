rm -Rf bin/*
javac src/*.java -d bin/ -Xlint
cd bin
java org.junit.runner.JUnitCore test.RentServiceTest
