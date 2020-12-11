# Phase 3

## Introduction

This document describes the requirements for the third phase of the Software Laboratory project.

## Requirements

### HTTP Server

The main requirement for the third phase is the delivery of an HTTP server able to handle all the `GET` commands developed on the first and second phases.
This server must use the HTTP (Hypertext Transfer Protocol) protocol to receive command requests and return their responses.
The response's contents must use the `text/plain`, `text/html` and `application/json` formats.

### Additional commands ###

* The console application must also implement the additional `LISTEN /`, which starts the HTTP server. This command receives a `port` parameter containing the TCP port where the server should listen for requests.

### Hypermedia

The responses for any successful request must fulfil the following requirements:

* When the response is represented in the HTML format and contains a list, then each item must include a link to the item detail.
For instance, the HTML document returned on a successful `GET` to `/cinemas` must include links to each course, pointing to `/cinemas/{cid}`.

* The responses must also contain the links required to ensure the navigability between all the resources, as defined in the following graph.
For instance, the document returned on a successful `GET` to `/cinemas/{cid}` must contain a link to `/cinemas`.

* All the representations must include a link to the root (home) representation.

![Link graph](/wiki/img/LS_Cinemas.png)
(Click [here](/wiki/img/LS_Cinemas.png) for a bigger picture)