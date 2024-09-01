DESCRIPTION = "A C++ base UPnP library, derived from Portable UPnP, a.k.a libupnp."
LICENSE = "GPL-2.0-only"

SRCREV = "a760b3278aee06408d9f50d0c54942f5ba9df989"
SRC_URI = "git://framagit.org/medoc92/npupnp;branch=master;protocol=https"

LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"
SRC_URI[sha256sum] = "a24388c3f238c2b21bfd265a1ed84c8001110efa69c309b22d7465402d8cca03"

inherit meson pkgconfig

S = "${WORKDIR}/git"

DEPENDS += " curl expat libmicrohttpd"
RDEPENDS:${PN} += " curl expat libmicrohttpd"

