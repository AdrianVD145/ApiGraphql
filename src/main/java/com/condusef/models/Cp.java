package com.condusef.models;

import java.util.List;

import org.infinispan.protostream.annotations.Proto;

@Proto
public record Cp(
List<CPMexicanoCache> cpMexicanoCacheList

){}