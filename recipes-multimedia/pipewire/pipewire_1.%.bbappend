DEPENDS += " libfreeaptx libfreeaptx-dev libldac"
RDEPENDS:${PN} += " libfreeaptx libldac"

EXTRA_OEMESON:remove = "-Dbluez5-codec-aptx=disabled"
EXTRA_OEMESON:remove = "-Dbluez5-codec-ldac=disabled"
EXTRA_OEMESON =+ " -Dbluez5-codec-aptx=enabled"
EXTRA_OEMESON =+ " -Dbluez5-codec-ldac=enabled"

