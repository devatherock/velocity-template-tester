#!/bin/bash
 
now=$(date +%s)
sed -i "s/{build_time}/$now/g" src/main/resources/public/index.html