<h1> Distributed Prime Number Generator Master Program </h1>
<h2>TODO</h2>
<ul>
    <li> add argument support for goal, number of nodes, prime sync chunk size, and work chunk size for better fine tuning
    <li> migrate to a larger datatype for prime numbers
    <li> optimize the chunk size
    <li> optimize the prime number synchronization/generate a chunk for sync
    <li> support for config file (json?)
</ul>
<hr><br>
<h2> HOW TO USE </h2>
<p>modify the <code>GOAL</code> in <code>src/main/com/alexcomeau/Main.java</code> for the goal ending prime  <br>
modify the <code>CHUNK_SIZE</code> in <code>src/main/com/alexcomeau/work/WorkManager.java</code> (Default: <code>50</code>) for better optimization for the worker strength and available bandwidth <br>
modify the port in <code>src/main/com/alexcomeau/Main.java</code><br>
compile using maven and a java jdk which  has a version greater than or equal to <code>11</code> <br>
run the compiled jar file that that has dependencies included, if on linux the default port of 1000 may require you to change to a higher port number or run as root.


