package com.condusef.cache.schema;


import org.infinispan.protostream.annotations.ProtoSchema;

import com.condusef.models.CPMexicanoCache;
import com.condusef.models.Cp;

import org.infinispan.protostream.GeneratedSchema;

@ProtoSchema(includeClasses = {Cp.class, CPMexicanoCache.class})
public interface CpSchema extends GeneratedSchema{  } 