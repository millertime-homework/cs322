	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 6) (CALL (NAME A_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t6 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 6))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_go:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
	mov 3,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 2)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l1]
! [MOVE (TEMP 3) (BINOP + (TEMP 2) (BINOP * (CONST 2) (NAME wSZ)))]
	mov %l1,%l0
	mov 2,%l2
	mov 4,%l3
	smul %l2,%l3,%l2
	mov %l2,%l2
	add %l0,%l2,%l0
	mov %l0,%l0
!>> Temp t3 assigned to reg %l2
	mov %l0,%l2
! [LABEL L0]
L0:
! [MOVE (MEM (TEMP 3)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (TEMP 3) (BINOP - (TEMP 3) (NAME wSZ))]
	mov %l2,%l0
	mov 4,%l3
	sub %l0,%l3,%l0
	mov %l0,%l0
	mov %l0,%l2
! [CJUMP > (TEMP 3) (TEMP 2) (NAME L0)]
	mov %l2,%l0
	mov %l1,%l3
	cmp %l0,%l3
	bg L0
	nop
! [MOVE (FIELD (PARAM 0) 0) (TEMP 2)]
	mov %l1,%l0
	ld [%fp+68],%l3
	st %l0,[%l3]
! [MOVE (TEMP 4) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
	mov 3,%l0
	mov 4,%l4
	smul %l0,%l4,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t4 assigned to reg %l4
	mov %o0,%l4
! [MOVE (MEM (TEMP 4)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l4]
! [MOVE (TEMP 5) (BINOP + (TEMP 4) (BINOP * (CONST 2) (NAME wSZ)))]
	mov %l4,%l0
	mov 2,%l5
	mov 4,%l6
	smul %l5,%l6,%l5
	mov %l5,%l5
	add %l0,%l5,%l0
	mov %l0,%l0
!>> Temp t5 assigned to reg %l5
	mov %l0,%l5
! [LABEL L1]
L1:
! [MOVE (MEM (TEMP 5)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l5]
! [MOVE (TEMP 5) (BINOP - (TEMP 5) (NAME wSZ))]
	mov %l5,%l0
	mov 4,%l6
	sub %l0,%l6,%l0
	mov %l0,%l0
	mov %l0,%l5
! [CJUMP > (TEMP 5) (TEMP 4) (NAME L1)]
	mov %l5,%l0
	mov %l4,%l6
	cmp %l0,%l6
	bg L1
	nop
! [MOVE (VAR 1) (TEMP 4)]
	mov %l4,%l0
	st %l0,[%fp-4]
! [MOVE (MEM (BINOP + (FIELD (PARAM 0) 0) (NAME wSZ))) (CONST 1)]
	mov 1,%l0
	ld [%fp+68],%l6
	ld [%l6],%l7
	mov 4,%i1
	add %l7,%i1,%l7
	st %l0,[%l7]
! [MOVE (MEM (BINOP + (FIELD (PARAM 0) 0) (BINOP * (CONST 2) (NAME wSZ)))) (CONST 2)]
	mov 2,%l0
	ld [%fp+68],%l7
	ld [%l7],%i1
	mov 2,%i2
	mov 4,%i3
	smul %i2,%i3,%i2
	mov %i2,%i2
	add %i1,%i2,%i1
	st %l0,[%i1]
! [MOVE (MEM (BINOP + (VAR 1) (NAME wSZ))) (CONST 3)]
	mov 3,%l0
	ld [%fp-4],%i1
	mov 4,%i2
	add %i1,%i2,%i1
	st %l0,[%i1]
! [MOVE (MEM (BINOP + (VAR 1) (BINOP * (CONST 2) (NAME wSZ)))) (CONST 4)]
	mov 4,%l0
	ld [%fp-4],%i1
	mov 2,%i2
	mov 4,%i3
	smul %i2,%i3,%i2
	mov %i2,%i2
	add %i1,%i2,%i1
	st %l0,[%i1]
! [CALLST (NAME print) ( (MEM (BINOP + (FIELD (PARAM 0) 0) (BINOP * (CONST 2) (NAME wSZ)))))]
