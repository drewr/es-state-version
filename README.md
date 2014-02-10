# stateversion

Creates a transport client to a cluster node, grabs its local state,
and prints the version.  Must be run on the machine where the node is
running to give sensible IPs and timestamp.

## Usage

    % ./stateversion -h
    usage: stateversion [CLUSTER] [HOST] [PORT]
    % ./stateversion drewr
    1392049201 10:20:01 192.168.56.1 fe80:0:0:0:8638:35ff:fe4f:6020%5 192.168.1.22 3

Defaults to `elasticsearch` `localhost` `9300`.  Last number is the state version.

## License

Copyright © 2014 Elasticsearch

Licensed under the Apache License, Version 2.0.
