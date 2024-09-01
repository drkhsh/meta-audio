DESCRIPTION = "Libupnpp provides a higher level C++ API over libnpupnp or libupnp."
LICENSE = "GPL-2.0-only"

SRCREV = "86cc0848a29ec92bec38bb9b5cbc7bf4cb3ac6c0"
SRC_URI = "git://framagit.org/medoc92/libupnpp;branch=master;protocol=https"

LIC_FILES_CHKSUM = "file://COPYING;md5=321bf41f280cf805086dd5a720b37785"
SRC_URI[sha256sum] = "a24388c3f238c2b21bfd265a1ed84c8001110efa69c309b22d7465402d8cca03"

inherit meson pkgconfig

S = "${WORKDIR}/git"

DEPENDS += " libnpupnp"
RDEPENDS:${PN} += " libnpupnp"

