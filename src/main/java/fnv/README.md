# fnv-java
[Fowler-Noll-Vo](https://en.wikipedia.org/wiki/Fowler–Noll–Vo_hash_function) FNV1 and FNV1a hash functions, in Java; supports hash lengths from 16 to to 1024 bits (including [xor folding](https://tools.ietf.org/html/draft-eastlake-fnv-12#section-3) for lengths which are not FNV constant sizes).

[![unlicense](https://img.shields.io/badge/un-license-green.svg?style=flat)](http://unlicense.org) [![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg)](https://github.com/RichardLitt/standard-readme)

## Table of Contents

- [Background](#background)
- [Install](#install)
- [Usage](#usage)
- [Contribute](#contribute)
- [License](#license)

## Background
I couldn't find any Java implementations of the FVN1 and FNV1a hashes that went above 64 bits, or included XOR folding. So I made one.

This project implements as closely as possible the IETF draft [The FNV Non-Cryptographic Hash Algorithm](https://tools.ietf.org/html/draft-eastlake-fnv-12), using Java's [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html) functionality to hold and compute the hashes.

The code is based on Stefan Santesson's sample in the IETF draft [Transport Layer Security (TLS) Cached Information Extension](https://tools.ietf.org/html/draft-ietf-tls-cached-info-08#appendix-A.2).

## Install
Download and insert package [fnv](src/fnv) into your project.

## Usage

You can test (at FNV constant lengths) using [nqv](https://github.com/nqv/fnv)'s excellent [javascript FNV hasher](https://nqv.github.io/fnv/).

## Contribute
[Contributor Covenant](http://contributor-covenant.org/version/1/3/0/)

## License

[Unlicense](LICENSE)
