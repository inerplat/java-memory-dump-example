# java-memory-dump-example

## Run command with VM Options
```
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/some/path -server -Xms2048m -Xmx2048m -javaagent:/some/path/apm-agent.jar -Delastic.apm.disable_bootstrap_checks=true -Delastic.apm.service_name=balcode -Delastic.apm.server_urls=https://YOUR_APM_HOST/ -Delastic.apm.environment=production -Delastic.apm.application_packages=com.example.balcode -jar ./build/libs/balcode-0.0.1.jar
```
---
## Heap Dump

### Visual VM
- https://visualvm.github.io/
<img width="1854" alt="image" src="https://user-images.githubusercontent.com/21287813/204844746-bb765442-b785-4c8b-8149-408105d098c4.png">


### Memory Analyzer (MAT)
- https://www.eclipse.org/mat/

<img width="1206" alt="image" src="https://user-images.githubusercontent.com/21287813/204844903-548501c8-f597-416e-9853-901f2b81b639.png">

### Elastic APM

<img width="1849" alt="image" src="https://user-images.githubusercontent.com/21287813/204850968-82b8ca53-a92d-4893-a88b-6a61b02cb01e.png">


---
## Native Memory Dump

```
apt update && apt install gdb binutils -y

jcmd <pid> VM.native_memory detail
```
```
pmap -x <pid> | sort -k 3 -n -r | more
```
```
vim /proc/<pid>/smaps
gdb -pid <pid>
> dump memory /file/path start end
```



