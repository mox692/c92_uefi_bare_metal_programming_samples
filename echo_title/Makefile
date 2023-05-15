# ref:
# https://qiita.com/kei-g/items/78665e0d7454e5e1e5ce
# https://github.com/kei-g/uefi-hello/blob/main/Makefile

CC=clang
CFLAGS+=--target=x86_64-pc-windows-msvc
CFLAGS+=-Iefi
CFLAGS+=-Oz
CFLAGS+=-Wall
CFLAGS+=-Werror
CFLAGS+=-ffreestanding
CFLAGS+=-flto
CFLAGS+=-fno-builtin
CFLAGS+=-fno-exceptions
CFLAGS+=-fno-rtti
CFLAGS+=-fno-stack-check
CFLAGS+=-fno-stack-protector
CFLAGS+=-fno-use-cxa-atexit
CFLAGS+=-fshort-wchar
CFLAGS+=-march=native
CFLAGS+=-mno-red-zone
CFLAGS+=-nostdlib
CFLAGS+=-pedantic

LD=clang
LDFLAGS+=--target=x86_64-pc-windows-msvc
LDFLAGS+=-Wl,/dll
LDFLAGS+=-Wl,/entry:efi_main
LDFLAGS+=-Wl,/nodefaultlib
LDFLAGS+=-Wl,/safeseh:no
LDFLAGS+=-Wl,/subsystem:efi_application
LDFLAGS+=-Wl,/version:1.0
LDFLAGS+=-ffreestanding
LDFLAGS+=-flto
LDFLAGS+=-fuse-ld=lld
LDFLAGS+=-march=native
LDFLAGS+=-nostdlib

all: fs/EFI/BOOT/BOOTX64.EFI

# mingw32 -> clang
# main.c -> main.scala

# 	mkdir -p fs/EFI/BOOT
# 	x86_64-w64-mingw32-gcc -Wall -Wextra -e efi_main -nostdinc -nostdlib \
# 	-fno-builtin -Wl,--subsystem,10 -o $@ $<

fs/EFI/BOOT/BOOTX64.EFI: main.c
	mkdir -p fs/EFI/BOOT
# compile
	$(CC) $(CFLAGS) -c $< -o main.o
# link
	$(LD) $(LDFLAGS) -Wl,/out:$@ main.o


run: fs/EFI/BOOT/BOOTX64.EFI
	sudo qemu-system-x86_64 -bios /usr/share/ovmf/OVMF.fd -hda fat:rw:fs

clean:
	rm -rf *~ fs

.PHONY: clean
