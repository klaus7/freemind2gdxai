# Freemind to gdx-ai
Converts freemind files to the gdx-ai format. (LibGDX-AI, see https://github.com/libgdx/gdx-ai and https://github.com/libgdx/gdx-ai/wiki/Behavior-Trees for an example of the file format)

The purpose of this little project is to get nice graphical editing of behaviour trees.

**This:**
![alt text][freemind1]

**becomes this:** (this is the example from the documentation to gdx-ai found here: https://github.com/libgdx/gdx-ai/wiki/Behavior-Trees)

```
import bark:"com.badlogic.gdx.ai.tests.btree.dog.BarkTask"
import care:"com.badlogic.gdx.ai.tests.btree.dog.CareTask"
import mark:"com.badlogic.gdx.ai.tests.btree.dog.MarkTask"
import walk:"com.badlogic.gdx.ai.tests.btree.dog.WalkTask"

root
  selector
    parallel
      care urgendProb:0.8
      alwaysFail
        com.badlogic.gdx.ai.tests.btree.dog.RestTask
    sequence
      bark times:2
      walk
      com.badlogic.gdx.ai.tests.btree.dog.BarkTask
      mark
```

## Build
mvn package

## Run
java -jar target/freemind2gdxai.jar -i test.mm

## Show Help
java -jar target/freemind2gdxai.jar -?



[freemind1]: https://raw.githubusercontent.com/klaus7/freemind2gdxai/master/doc/freemind1.png "FreeMind Screenshot"
