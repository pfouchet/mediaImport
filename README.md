# mediaImport

This program allows one to update reference data with Media. Currently appliances and techniques are handled.

Those settings are needed :

* VM options : -Ddcp.host=http://'host'/common-api -Duse.apipro.proxy=true -Droot.log.path=C:\var\log\ofs\mediaImport
* program arguments : 'resourceName' 'filePathWithoutStartingSlash'

It mimics ETL and was designed to be quick & dirty.
A good thing could be to implement using Talend.

## Reader
Currently CSV file and directory based reading are supported.

## Transformer
Transform DTO to final format.

## Writer
PUT on the common-api.
