## Distributed Prime Number Generator Worker Program
<hr>
<h2> TODO </h2>
<ul>
    <li>argument support for master node and master port specifications
    <li> customization for sender port
    <li>config files (JSON?)
    <li> make configuring constants a lot easier
</ul>
<hr>
<h2> How To Use </h2>
<p> you can change the master port and master IP in <code>\src\main\java\com\alexcomeau\networking\SocketManager.java:getSocket()</code> <i>(this will be simplified in the future)</i><br>
to run the program compile using maven and a java jdk which  has a version greater than or equal to <code>11</code> <br>
run the compiled jar file that that has dependencies included, and make sure there is a stable network connection between the worker and the master