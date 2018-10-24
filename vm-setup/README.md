# Debezium Microservices Lab Vagrant image

This is a source repository for [Vagrant image](https://app.vagrantup.com/debezium/boxes/microservices-lab) serving as a Debezium microservices lab environment.

## Image usage
There are two ways how to use the image
* (recommended) Use provided `Vagrant` file. In this case just download the `Vagrantfile` into an arbitrary directory and issue `vagrant up` command.
After a while the new virtual machine will be spawned and you could log to it via `vagrant ssh`.
* (advanced) Create the envrionment form scratch using `vagrant init debezium/microservices-lab` command.
In this case the user is responsible for configuration of networking, port-forwarding and capacity allocation.

## Building the image
To build the image from scratch it is necessary to folow this procedure
* Use `Vagrantfile.build`as the Vagrant configuration file.
* Start the virtual machine using `vagrant up`.
* Log into virtual machine using `vagrant ssh`.
* As `root` execute
```
$ dnf clean all

# It is expected that the following command will fill the disk and generate an error
$ sudo dd if=/dev/zero of=/EMPTY bs=1M
$ sudo rm -f /EMPTY
```
* Exit the virtual machine
* Build the box using `vagrant package --vagrantfile Vagrantfile.init --output /tmp/microservices-lab.box`.
* To test the box use `vagrant box add debezium/microservices-lab /microservices-lab.box` and run it.
* Optionally upload the file into via [Vagrant Cloud GUI](https://app.vagrantup.com/debezium/boxes/microservices-lab/)
