package com.sem.customsounds;

public enum Sound {
  COLLECTION_LOG_SLOT("ColLogSlotCompleted_r1.wav");

   private final String resourceName;

  Sound(String resNam) { 
    resourceName = resNam;
  } 
 String getResourceName() {
    return resourceName;
  }
}
