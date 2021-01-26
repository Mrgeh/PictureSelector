PictureSelector
===============

PictureSelector

geh
PictureSelector 图片相册及相机选择，目前暂时适配android10

需要在application节点下面加 android:requestLegacyExternalStorage="true"


才能适配sdk29及之后的版本


29以前的则不用。androidQ 的适配规则 

以 Android 10（API 级别 29）及更高版本为目标平台的应用在默认情况下被赋予了对外部存储设备的分区访问权限（即分区存储）。此类应用只能看到本应用专有的目录（通过 Context.getExternalFilesDir() 访问）以及特定类型的媒体。
这种分区存储限制了应用通过绝对路径去打开文件，不能通过File file = new File(filePath)去打开，这类路径不具有直接内核访问权限。要访问此类文件，应用必须使用 MediaStore，并调用 openFile() 等方法。
