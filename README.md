# mediaImport

This program allows one to update reference data with Media. Currently appliances and techniques are handled.

Those settings are needed :

* VM options : -Ddcp.host=http://'host'/common-api -Duse.apipro.proxy=true -Droot.log.path=C:\var\log\ofs\mediaImport -Ddcp.login=LOGIN -Ddcp.password=PWD
* program arguments : 'resourceName' 'filePathWithoutStartingSlash' such as *techniques input/diff5_4_3-5_5_1_2MediaTechnique.csv*

It mimics ETL and was designed to be quick & dirty.

## Reader
Currently CSV file and directory based reading are supported.

## Transformer
Transform DTO to final format.

## Writer
PUT on the common-api.
