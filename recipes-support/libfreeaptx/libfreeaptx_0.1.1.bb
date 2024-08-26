DESCRIPTION = "Free implementation of Audio Processing Technology codec (aptX) forked from libopenaptx 0.2.0"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRCREV = "c176b7de9c2017d0fc1877659cea3bb6c330aafa"
PV = "+git${SRCPV}"
SRC_URI = "git://github.com/iamthehorker/libfreeaptx.git;branch=master;protocol=https"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"
PROVIDES = "${PN} ${PN}-dev ${PN}-dbg"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += " 'CC=${CC} -fPIC' 'CFLAGS=${CFLAGS} -fPIC' LDFLAGS='${LDFLAGS}'"

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${exec_prefix}
}

FILES:${PN} = "\
    /usr/bin/freeaptxenc \
    /usr/bin/freeaptxdec \
    /usr/lib/libfreeaptx.so \
    /usr/lib/libfreeaptx.so.0 \
    /usr/lib/libfreeaptx.so.0.1.1 \
"

FILES:${PN}-dev = "\
    /usr/include/freeaptx.h \
    /usr/lib/pkgconfig/libfreeaptx.pc \
"

FILES:${PN}-dbg = "\
    /usr/bin/.debug \
    /usr/lib/.debug \
"

INSANE_SKIP:${PN} += " already-stripped dev-so"
INSANE_SKIP:${PN}-dev += " already-stripped"
INSANE_SKIP:${PN}-dbg += " already-stripped"
INHIBIT_PACKAGE_STRIP = "1"
