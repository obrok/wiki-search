# wiki-search

This project exposes a simple JSON API to search for wiki articles' by abstract
and title.

## Prerequisites

You'll need a working installation of Elasticsearch >= 1.4.0

## Installation

1. Clone the repo
1. lein deps
1. lein storage:create
1. lein seed - this will download a wiki data dump and index it, it's a lengthy
   operation but it streams the file, so you can stop it at any time and what
   got indexed up to that point will stay there
1. lein ring server

## Usage

To create the elasticsearch index

    $ lein storage:create

To destroy the elasticsearch index

    $ lein storage:destroy

To fill out the index

    $ lein seed

To start the app (see [lein-ring](https://github.com/weavejester/lein-ring) for
more information)

    $ lein ring server

To run tests - note you'll also need your elasticsearch setup for this

    $ lein test

## Options

You can customize the elasticsearch endpoint by setting an environment variable

    $ ELASTICSEARCH="http://127.0.0.1:9200" lein test

When running the seed operation you can select a wiki dump file to fetch

    $ DUMP_FILE="http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-abstract23.xml" lein seed

## License

Copyright © 2015 Pawel Obrok

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
