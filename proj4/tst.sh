#!/bin/sh

gcc -Wall driver.c scan.s -o main
./main > tmp.out
diff tmp.out test_out
rm tmp.out
rm -f main