scalaVersion := "3.2.2"

enablePlugins(ScalaNativePlugin)

// set to Debug for compilation details (Info is default)
logLevel := Level.Debug

// import to add Scala Native options
import scala.scalanative.build._

// defaults set with common options shown
nativeConfig ~= { c =>
  // c.withLTO(LTO.none) // thin
  //   .withMode(Mode.debug) // releaseFast
  //   .withGC(GC.immix) // commix
  val c1 = c.withCompileOptions(
    c.compileOptions ++ Seq(
      // "--target=x86_64-pc-windows-msvc",
      "--target=x86_64-w64-mingw32",
      "-Iefi",
      "-Oz",
      // "-Wall", // err
      // "-Werror",
      // "-ffreestanding", // err
      // "-flto", // err // not needed? https://qiita.com/kaityo256/items/a822fc462a4de6ddd8e7
      "-fno-builtin",
      // "-fno-exceptions", // err
      "-fno-rtti",
      "-fno-stack-check",
      "-fno-stack-protector",
      "-fno-use-cxa-atexit",
      "-fshort-wchar",
      // "-march=native",
      // "-mno-red-zone",
      "-nostdlib"
      // "-pedantic" // err // not needed? https://stackoverflow.com/questions/2855121/what-is-the-purpose-of-using-pedantic-in-the-gcc-g-compiler
    )
  )

  val c2 = c1.withTargetTriple("x86_64-pc-windows-msvc")
  // debug conf
  println(s"config: ${c2.toString}")
  c2
}

nativeConfig ~= { c =>
  c.withLinkingOptions(
    c.linkingOptions ++ Seq(
      // "--target=x86_64-w64-mingw32",
      // "-Wl,/dll",
      // "-Wl,/entry:efi_main",
      // "-Wl,/nodefaultlib",
      // "-Wl,/safeseh:no",
      // "-Wl,/subsystem:efi_application",
      // "-Wl,/version:1.0",
      // "-ffreestanding",
      // "-flto",
      // "-fuse-ld=lld",
      // "-march=native",
      // "-nostdlib"
    )
  )
}
