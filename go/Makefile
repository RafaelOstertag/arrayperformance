GO ?= go
GOROOT ?= $(HOME)/go
GOPATH ?= $(shell pwd)

all:
	env GOROOT="$(GOROOT)" GOPATH="$(GOPATH)" $(GO) build go-array

clean:
	rm -rf bin pkg
	rm -f go-array