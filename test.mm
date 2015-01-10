<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1420902919984" ID="ID_1471303437" MODIFIED="1420904775492" TEXT="root">
<richcontent TYPE="NOTE"><html>
  <head>
    
  </head>
  <body>
    <p>
      import bark:&quot;com.badlogic.gdx.ai.tests.btree.dog.BarkTask&quot;
    </p>
    <p>
      import care:&quot;com.badlogic.gdx.ai.tests.btree.dog.CareTask&quot;
    </p>
    <p>
      import mark:&quot;com.badlogic.gdx.ai.tests.btree.dog.MarkTask&quot;
    </p>
    <p>
      import walk:&quot;com.badlogic.gdx.ai.tests.btree.dog.WalkTask&quot;
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1420902923849" ID="ID_1882911590" MODIFIED="1420904731220" POSITION="right" TEXT="selector">
<node CREATED="1420902931293" ID="ID_58867346" MODIFIED="1420904781701" TEXT="parallel">
<node CREATED="1420904795510" ID="ID_1148538429" MODIFIED="1420906121592" TEXT="care urgentProb:0.8"/>
<node CREATED="1420904805574" ID="ID_813405828" MODIFIED="1420904808305" TEXT="alwaysFail">
<node CREATED="1420904823262" ID="ID_1241650770" MODIFIED="1420904826195" TEXT="com.badlogic.gdx.ai.tests.btree.dog.RestTask"/>
</node>
</node>
<node CREATED="1420902928094" ID="ID_1073582160" MODIFIED="1420906132809" TEXT="sequence">
<node CREATED="1420904832551" ID="ID_94135502" MODIFIED="1420904835855" TEXT="bark times:2"/>
<node CREATED="1420904837038" ID="ID_199030800" MODIFIED="1420904838106" TEXT="walk"/>
<node CREATED="1420904843304" ID="ID_1433978963" MODIFIED="1420904844126" TEXT="com.badlogic.gdx.ai.tests.btree.dog.BarkTask"/>
<node CREATED="1420904844702" ID="ID_432625342" MODIFIED="1420904846552" TEXT="mark"/>
</node>
</node>
</node>
</map>
